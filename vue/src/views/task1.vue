<template>
  <div>
    <div style="width: 500px; height: 500px; background-color: white;
    padding: 50px; margin: 50px auto;">
      <!-- form area -->
      <el-form :model="form">
        <el-form-item label="Principle (in dollars)">
          <el-input v-model="form.principal" style="margin-top: 2px"></el-input>
        </el-form-item>
        <el-form-item label="Amortization (in years)">
          <el-input v-model="form.amortization" style="margin-top: 2px"></el-input>
        </el-form-item>
        <el-form-item label="Interest (as an annual percent)">
          <el-input v-model="form.interest" style="margin-top: 2px"></el-input>
        </el-form-item>
      </el-form>
      <!-- button -->
      <div style="text-align: center; margin-top: 15px; margin-bottom: 15px">
        <el-button style="width: 100%; height: 45px; font-size: 20px" type="primary" @click="mCal">MONTHLY PAYMENT</el-button>
      </div>
      <!-- output -->
      <div style="text-align: center; font-size: 40px; font-weight: bold; color: red; font-family: Arial">{{ result }}</div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "task1",
  data() {
    return {
      form: {
        principal: "",
        amortization: "",
        interest: "",
      },
      result: "",
    }
  },

  methods: {
    mCal() {
      request.get("/mortgage/computePayment/" + this.form.principal + "&" + this.form.amortization + "&" + this.form.interest).then(res => {
        this.result = "$" + res.data.toFixed(2);
      })
    }
  }
}
</script>

<style scoped>

</style>