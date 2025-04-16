<template>
  <div>
    <p>Naslov</p>
    <textarea
        class="title-input"
        v-model="title"
        placeholder="Naslov"
    >
    </textarea>
    <p>Kategorija</p>
    <select v-model="kategorijaName">
      <option v-for="option in kategorije" :key="option.id" :value="option.title">
        {{ option.title }}
      </option>
    </select>
    <p>Sadrzaj</p>
    <textarea
        class="content-input"
        v-model="content"
        placeholder="Sadrzaj">
    </textarea>
    <p>Tagovi</p>
    <textarea
        class="title-input"
        v-model="tagovi"
        placeholder="tagovi">
    </textarea>

    <button @click="sendNewNewsClick(title,content,tagovi,kategorijaName)">Add</button>

  </div>
</template>

<script>
export default {
  name: "NewNews",
  data() {
    return {
      news: {},
      title:"",
      content:"",
      kategorijaName:null,
      id_kategprija:2,
      selectedKategorija: null,
      tagovi:"",
      kategorije: []
    }
  },
  created() {
    this.$axios.get('/api/kategorije/all').then((response) => {
      this.kategorije = response.data;
      //this.autor_id=localStorage.getItem('id');
    });
  },
  mounted() {
    this.$axios.get('/api/kategorije/all').then((response) => {
      this.kategorije = response.data;
      this.kategorijaName=this.kategorije[0].title;
    });
  },
  methods:{
    sendNewNewsClick(title,content,tagovi,kategorijaName ) {
      // this.content=title;
      // this.title=content;
      // this.tagovi=tagovi;
      // this.selectedKategorija=selectedKategorija;
      // this.$nextTick(() => {
      //   console.log(this.content);
      //   console.log(this.title);
      //   console.log(this.selectedKategorija); // Print the selected category
      //   console.log(this.tagovi);
      // });
      if(title !="" && content !="" && tagovi !="" && kategorijaName !="" ) {
        const autor_id = localStorage.getItem('id');
        this.$axios.post('/api/news', {
          title, content, tagovi, kategorijaName, autor_id
        })
        this.$router.push('/news');
      }else{
        window.alert('Sva polja su obavezna');

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