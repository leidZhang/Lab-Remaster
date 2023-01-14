<template>
  <div id="app">
    <!-- Header -->
    <div style="text-align: center; margin-bottom: 2px; background-color: white;">
      <span style="font-size: 45px; font-weight: bold">Lab Remaster</span>
    </div>
    <!-- Nav menu -->
    <div>
      <el-menu :default-active="$route.path" router class="el-menu-demo" mode="horizontal">
        <el-menu-item style="margin-left: 10px" index="/task1">Task1(MCalc)</el-menu-item>
        <el-menu-item style="margin-left: 10px" index="/task2">Task2(MCalcPro)</el-menu-item>
        <el-menu-item style="margin-left: 10px" index="/task3">Task3(Caps)</el-menu-item>
      </el-menu>
    </div>

    <router-view/>
  </div>
</template>

