<template>
  <div>
    <p>Naslov</p>
    <textarea
        class="title-input"
        v-model="title"
        >
    </textarea>
    <p>Opis</p>
    <textarea
        class="content-input"
        v-model="content"
        placeholder="Content">
    </textarea>

    <button @click="saveClick(id,title,content)">Edit</button>

  </div>
</template>

<script>

export default {
name: "SingleKategorija",

  data() {
    return {
      kategorija: {},
      id:9,
      title:"",
      content:""
    }
  },

  created() {
    this.$axios.get(`/api/kategorije/${this.$route.params.id}`).then((response) => {
      this.kategorija = response.data;
      this.id=this.kategorija.id;
      this.title = this.kategorija.title;
      this.content = this.kategorija.content;
    });
  },
  methods:{
    saveClick(id,title,content) {
      if (title != "" && content != "") {

        this.$axios.post('/api/kategorije/replace', {
          id, title, content
        })
        this.$router.push('/kategorije');
      }
      else {
        window.alert('sva polja su obavezna.');
      }
    }
  }
}

</script>


<style scoped>
    .title-input{
      width: 50%;
      height: 50px;
    }
    .content-input{
    width: 100%;
    height: 200px;
  }

</style>