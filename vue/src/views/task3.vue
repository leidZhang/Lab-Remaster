<template>
  <div>
    <div style="width: 500px; min-height: 400px; background-color: white;
    padding: 50px; margin: 50px auto;">
      <div>
        <div style="float: left; font-size: 25px; font-weight: bold; color: mediumpurple; font-family: Arial">SCORE = {{ score }}</div>
        <div style="float: right; font-size: 25px; font-weight: bold; color: gray; font-family: Aria">{{ status }}</div>
      </div>
      <div style="clear: both; text-align: center; margin-bottom: 2px; font-size: 20px; font-style: italic;
      font-weight: bold; color: mediumblue">
        {{ question }}
      </div>
      <div>
        <el-input v-model="userAnswer" placeholder="Your answer" style="float: left; width: 83%; font-size: 18px"></el-input>
        <el-button style="float: right" type="primary" @click="submit" :disabled="flag">DONE</el-button>
      </div>
      <div style="margin-top: 10px; font-size: 18px; color: grey;white-space: pre-wrap">{{ userResult }}</div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "task3",

  data() {
    return {
      score: 0,
      qNum: 1,
      status: "",
      question: 'Loading...',
      userAnswer: "",
      answer: "",
      userResult: '',
      flag: false,
    }
  },

  created() {
    this.status = "Q# " + this.qNum;
    this.load();
  },

  methods: {
    load() {
      request.get("/game/getQuestion").then(res => {
        this.question = this.sepQuestion(res.data);
        this.answer = this.sepAnswer(res.data);
        // console.log(this.question);
        console.log(this.answer);
      })
    },

    submit() {
      let result = "";
      // Is the user input the true answer?
      if (this.userAnswer.toUpperCase() == this.answer.toUpperCase()) {
        this.score += 1;
      }
      // print result
      result += "\n" + "Q# " + this.qNum + ": " + this.question + "\n";
      result += "Your answer: " + this.userAnswer + "\n";
      result += "Correct answer: " + this.answer + "\n";
      this.userResult = result + this.userResult;
      // load the next question
      if (this.qNum <= 9) { // 10 questions in total
        this.load();
        this.qNum += 1;
        this.status = "Q# " + this.qNum;
      } else { // end game
        this.flag = true;
        this.question = "-- No more questions this round --"
        this.status = "GAME OVER!"
      }
      this.userAnswer = ""; // auto clear
    },

    sepQuestion(data) { // get question
      let result = "";

      for(let i=0; i<data.length; i++) {
        if (data[i] == '\n') {
          break;
        }
        result += data[i];
      }

      return result;
    },

    sepAnswer(data) { // get answer
      let result = "";
      let flag = 0;

      for(let i=0; i<data.length; i++) {
        if (flag == 1) {
          result += data[i];
        }
        if (data[i] == '\n') {
          flag = 1;
        }
      }

      return result;
    }
  }
}
</script>

<style scoped>

</style>