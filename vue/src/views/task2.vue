<template>
  <div>
    <!--<h1>Hello World!</h1>-->
    <div style="width: 500px; height: auto; background-color: white;
    padding: 50px; margin: 50px auto;">
      <!-- form area -->
      <el-form :model="form">
        <el-form-item>
          <el-input v-model="form.principal" style="margin-bottom: 5px; font-size: 18px;" placeholder="Cash Price"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.amortization" style="margin-bottom: 5px; font-size: 18px;" placeholder="Amortization"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.interest" style="margin-bottom: 5px; font-size: 18px;" placeholder="Interest (%)"></el-input>
        </el-form-item>
      </el-form>
      <!-- button -->
      <div>
        <el-button style="font-weight: bold; width: 100%; height: 45px;
        font-size: 20px" type="primary" @click="mCalPro">A N A L Y Z E</el-button>
      </div>
      <!-- output -->
      <div id="message" style="margin-top: 5px">
        <div style="margin-bottom: 10px;">{{ payMessage }}</div>
        <div style="margin-bottom: 10px;">{{ titleMessage }}</div>
        <div v-html="detailMessage" style="white-space: pre-wrap"></div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "task2",
  data() {
    return {
      form: {
        principal: '',
        amortization: '',
        interest: '',
      },
      payMessage: '',
      titleMessage: '',
      detailMessage: '',
    }
  },

  methods: {
    mCalPro() {
      request.get("/mortgage/computePayment/" + this.form.principal + "&" + this.form.amortization + "&" + this.form.interest).then(res => {
        let payment = parseFloat(res.data.toFixed(2)); // round up to 2 digits after the decimal point
        this.payMessage = "Monthly payment = " + payment.toLocaleString();
      })

      this.titleMessage = "By making this payments monthly for " + this.form.amortization + " years, " +
          "the mortgage will be paid in full. But if you terminate the mortgage on its " +
          "nth anniversary, the balance still owing depends on n as shown below: ";

      request.get("/mortgage/balancePerYear/" + this.form.principal + "&" + this.form.amortization + "&" + this.form.interest).then(res => {
        this.detailMessage = res.data; // raw data
      })
    }
  }
}
</script>

<style scoped>
#message {
  min-height: 310px;
}
</style>