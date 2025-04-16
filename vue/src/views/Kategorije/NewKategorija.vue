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

    <button @click="sendNewKategorijaClick(title,content)">Add</button>

  </div>
</template>

<script>
export default {
  name: "NewKategorija",
  data() {
    return {
      kategorija: {},
      title:"",
      content:""
    }
  },
  methods:{
    sendNewKategorijaClick(title,content){
      //this.content=title;
      //this.title=content;
      if(title !="" && content !="" ) {
        this.$axios.post('/api/kategorije', {
          title, content
        })
        this.$router.push('/kategorije');
      }else {
        window.alert('sva polja su obavezna.');
      }
    }
  }

}
</script>

<style scoped>

</style>