<template>
  <div>
    <p>Sadrzaj</p>
    <textarea
        class="title-input"
        v-model="content"
        placeholder="Sadrzaj"
    >
    </textarea>
    <p>auotr</p>
    <input
        class="content-input"
        v-model="autor"
        placeholder="autor"/>

    <button @click="sendNewKategorijaClick()">click me</button>

  </div>
</template>

<script>
export default {
  name: "NewComment",
  data() {
    return {
      coment:{},
      autor:"",
      content:"",
      newsId: ''
    }
  }, created() {
    this.newsId = this.$route.query.newsId;
  },
  methods:{
    sendNewKategorijaClick(){
      const newsId=this.newsId;
      const autor=this.autor;
      const content=this.content;

      console.log(newsId);
      console.log(autor);
      console.log(content);
      this.$axios.post('/api/comments',{
        newsId,autor,content
      })
      this.$router.push('/news/read/'+newsId);

    }
  }

}
</script>

<style scoped>

</style>