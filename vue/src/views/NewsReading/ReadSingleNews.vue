<template>
  <div>
    <p>Naslov</p>
    <H1>{{ title }}</H1>
    <p>Kategorija</p>
    <H1>{{ selectedKategorija }}</H1>
    <p>Sadrzaj</p>
    <H1>{{ content }}</H1>
    <p>Autor</p>
    <H3>{{userOBJ.ime}} {{userOBJ.prezime}}</H3>
    <p>Tagovi</p>
    <ul>
      <li v-for="tag in tags" :key="tag.id" @click="searchTags(tag.name)">
         {{ tag.name }}
      </li>
    </ul>
    <button @click="newComent()">Add comment</button>
    <div class="container">
      <button @click="back()">back</button>
      <h3>{{ current }}</h3>
      <button @click="next()">next</button>
    </div>
    <ul>
      <li v-for="comment in comments" :key="comment.id" class="list-item">
        Comment: <br>{{ comment.content }}<br>
        Autor: {{ comment.autor }}<br>      </li>
    </ul>
  </div>
</template>


<script>
export default {
  name: "ReadSingleNews",
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
      userOBJ: {},
      click: null,
      selectedKategorija: null,
      kategorije: [],
      tags:[],
      userId:null,
      username:"",
      id_news:null,
      current:1,
      maxPages:1,
      maxElements:1,
      comments:[]
    }
  },

  created() {
    this.$axios.get(`/api/public/news/${this.$route.params.id}`).then((response) => {
      this.news = response.data;
      this.title = this.news.title;
      this.content = this.news.content;
      this.userId=this.news.autor_id;
      this.id_news=this.news.id;
      this.userOBJ=this.news.userOBJ;
      this.selectedKategorija = this.news.kategorijaOBJ.title; // Set the initial value
    });
    this.$axios.get('/api/public/kategorije/all').then((response) => {
      this.kategorije = response.data;
    });
    this.$axios.get(`/api/tags/${this.$route.params.id}`).then((response) => {
      this.tags = response.data;
    });
    this.$axios.get(`/api/comments/${this.$route.params.id}?page=${this.current}`).then((response) => {
      this.comments = response.data;
    });
    this.$axios.get(`/api/comments/max?id=${this.$route.params.id}`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },
  updated() {
    this.$axios.get(`/api/comments/${this.$route.params.id}?page=${this.current}`).then((response) => {
      this.comments = response.data;
    });
    this.$axios.get(`/api/comments/max?id=${this.$route.params.id}`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },

  methods:{
    saveClick(title,content){
      this.content=title;
      this.title=content;
    },
    searchTags(tag) {
      console.log(tag.name);
      this.$router.push('/newsTag/'+tag);
    },
    newComent() {
      console.log("TREnutni"+this.id_news);
      this.$router.push({
        path: "/newComment",
        query: { newsId: this.id_news }
      });
    },
    back(){
      if(this.current!=1)
      {
        this.current = this.current - 1;
        this.$axios.get(`/api/comments/${this.$route.params.id}?page=${this.current}`).then((response) => {
          this.comments = response.data;
        });
        console.log(this.current)
      }
    },
    next(){
      if(this.current!=this.maxPages)
      {
        this.current = this.current + 1;
        this.$axios.get(`/api/comments/${this.$route.params.id}?page=${this.current}`).then((response) => {
          this.comments = response.data;
        });
        console.log(this.current)
      }
      console.log(this.current)
    },
    formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };
      return date.toLocaleDateString('en-US', options);
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
.list-item {
  margin-bottom: 10px; /* Increase or adjust the value as needed */
}
.container {
  display: flex;
  align-items: center;
}
button {
  margin-right: 10px;
}
</style>