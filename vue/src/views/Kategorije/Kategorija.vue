<template>
  <div>
    <h2>
      Naslov:{{kategorija.title}}
    </h2>
    <h2>
      Opis:{{kategorija.content}}
    </h2>
  </div>


</template>

<script>
export default {
  name: "Kategorija",
  props: {
    kategorija: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>