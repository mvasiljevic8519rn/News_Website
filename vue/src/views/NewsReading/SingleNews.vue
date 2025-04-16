<template>
<div>
  <p>Naslov</p>
    <textarea
        class="title-input"
        v-model="title"
        placeholder="Naslov"
    ></textarea>
  <p>Kategorija</p>

  <select v-model="selectedKategorija">
    <option v-for="option in kategorije" :key="option.id" :value="option.title">
      {{ option.title }}
    </option>
  </select>
  <p>Sadrzaj</p>
    <textarea
        class="content-input"
        v-model="content"
        placeholder="Sadrzaj"
    ></textarea>
  <p>Tagovi</p>
  <textarea
      class="content-input"
      v-model="tagovi"
      placeholder="Tagovi"
  ></textarea>
  <button @click="saveClick(id,title,content,tagovi)">Add</button>

</div>
</template>

<script>

export default {
  name: "SingleNews",

  data() {
    return {
      news: {},
      id: null,
      title: '',
      content: '',
      formattedDate: '',
      clicks: null,
      kategorija: null,
      komentari: [],
      autor_id: null,
      kategorijaOBJ: {},
      userOBJ: null,
      click: null,
      tagovi:"",
      tags:[],
      selectedKategorija: null,
      kategorije: []
    }
  },

  created() {
    this.$axios.get(`/api/news/${this.$route.params.id}`).then((response) => {
      this.news = response.data;
      this.title = this.news.title;
      this.content = this.news.content;
      this.tags =this.news.tags;
      this.id=this.news.id;

      this.selectedKategorija = this.news.kategorijaOBJ.title; // Set the initial value
    });
    this.$axios.get('/api/kategorije/all').then((response) => {
      this.kategorije = response.data;
    });
    this.$axios.get(`/api/tags/${this.$route.params.id}`).then((response) => {
      this.tags = response.data;
      this.tagovi=this.tags.map(tag => tag.name).join(',')
    });
  },
  mounted() {
    this.$axios.get('/api/kategorije/all').then((response) => {
      this.kategorije = response.data;
    });
  },

  methods:{
    saveClick(id,title,content,tagovi){
         const kategorijaName=this.selectedKategorija;
         console.log(title+content+tagovi+"   "+kategorijaName+"   "+id);

      if(title !="" && content !="" && tagovi !="" && kategorijaName !="" ) {

        this.$axios.post('/api/news/replace', {
          id, title, content, tagovi, kategorijaName
        })
        this.$router.push('/news');
      }else {
        window.alert('sva polja su obavezna.');
      }
    }
  }
}

</script>

<style scoped>
.title-input{
  width: 75%;
  height: 50px;
}
.content-input{
  width: 100%;
  height: 200px;
}

</style>