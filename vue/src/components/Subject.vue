<template>
  <div>
    <h2>
      Predmet: {{ subject.title }}
    </h2>

    <h5>Opis:</h5>
    <p>
      {{ subject.content }} <br><br>
    </p>
  </div>
</template>

<script>

export default {
  name: "Subject",
  props: {
    subject: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>
