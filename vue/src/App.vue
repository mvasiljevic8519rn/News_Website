<template>
  <div>
    <NavBarReader/>
<!--    <Login/>-->
    <div class="container">
      <router-view/>
    </div>
  </div>
</template>

<script>
import NavBarReader from "@/views/NewsReading/NavBarReader";
//import login from "@/views/Login";
export default {
  components: {NavBarReader},
}
</script>
