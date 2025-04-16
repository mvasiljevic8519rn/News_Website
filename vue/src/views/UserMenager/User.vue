<template>
  <div>
    <h2>
      Email: {{ user.email }}
    </h2>
    <h2>
      Ime: {{ user.ime }}
    </h2>
    <h2>
      Prezime: {{ user.prezime }}
    </h2>
    <h2>
      vrsta:{{user.role}}
    </h2>
    <h2>
      status:{{user.aktivnost}}
    </h2>
  </div>
</template>

<script>

export default {
  name: "User",
  props: {
    user: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>