<template>
  <div>
    <h2>
      id: {{ artical.id }}
    </h2>
    <h2>
      title: {{ artical.title }}
    </h2>
    <h2>
      content: {{ artical.content }}
    </h2>
    <h2>
      autor: {{ artical.autor_name }}
    </h2>
    <h2>
      content: {{ artical.clicks }}
    </h2>
    <h2>
      kategorija: {{ artical.kategorija}}
    </h2>

  </div>
</template>

<script>

export default {
  name: "Artical",
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
