<template>
  <div>
    <artical :artical="artical"></artical>
  </div>
</template>

<script>
import Artical from "../components/Artical";
export default {
  name: "SingleArtical",
  components: {Artical},
  data() {
    return {
      artical: {},
    }
  },
  created() {
    this.$axios.get(`/api/artical/${this.$route.params.id}`).then((response) => {
      this.artical = response.data;
    });
  }
}
</script>

<style scoped>

</style>
