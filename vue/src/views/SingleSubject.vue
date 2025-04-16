<template>
  <div>
    <subject :subject="subject"></subject>
  </div>
</template>

<script>
import Subject from "../components/Subject";
export default {
  name: "SingleSubject",
  components: {Subject},
  data() {
    return {
      subject: {},
    }
  },
  created() {
    this.$axios.get(`/api/subjects/${this.$route.params.id}`).then((response) => {
      this.subject = response.data;
    });
  }
}
</script>

<style scoped>

</style>
