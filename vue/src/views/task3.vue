<template>
  <div>
    <div style="width: 500px; height: 400px; background-color: white;
    padding: 50px; margin: 50px auto;">
      <div>
        <div style="float: left; font-size: 25px; font-weight: bold; color: mediumpurple">SCORE = {{ score }}</div>
        <div style="float: right; font-size: 25px; font-weight: bold; color: gray">Q# {{ qNum }}</div>
      </div>
      <div style="clear: both; text-align: center; margin-bottom: 2px; font-size: 20px; font-style: italic;
      font-weight: bold; color: mediumblue">
        {{ question }}
      </div>
      <div>
        <el-input placeholder="Your answer" style="float: left; width: 83%; font-size: 18px"></el-input>
        <el-button style="float: right" type="primary">DONE</el-button>
      </div>
      <div style="margin-top: 2px; font-size: 18px; color: grey;">{{ result }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "task3",
  data() {
    return {
      score: 0,
      qNum: 1,
      question: 'Loading...',
      result: '',
    }
  }
}
</script>

<style scoped>

</style>