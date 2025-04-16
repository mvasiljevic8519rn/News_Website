<template>
  <div id="news">
    <img alt="Vue logo" src="../../assets/logo.png">
    <h1 class="mt-4">News</h1>

    <button @click="newNewsClick">Nova Vest</button>

    <div class="row">
      <div class="col-4">
        <table class="table">
          <thead>
          <tr>
<!--            <th scope="col">ID</th>-->
            <th scope="col">Title</th>
            <th scope="col">Autor</th>
            <th scope="col">Date</th>
            <th scope="col">Edit</th>
            <th scope="col">Delite</th>

          </tr>
          </thead>
          <tbody>
          <tr v-for="artical in news" :key="artical.id" @click="selectedArtical = artical">
<!--            <th scope="row">{{ artical.id }}</th>-->
            <td> {{artical.title}}</td>
            <td>{{artical.userOBJ.ime}} {{artical.userOBJ.prezime}} </td>
            <td>{{ formatTimestamp(artical.date) }}</td>
            <td>
              <button @click="editClick(artical)">Edit</button>
            </td>
            <td>
              <button @click="deliteClick(artical)">Delite</button>
            </td>
          </tr>
          </tbody>
        </table>
        <div class="container">
          <button @click="back()">back</button>
          <h3>{{ current }}</h3>
          <button @click="next()">next</button>
        </div>
      </div>

    </div>
  </div>



</template>

<script>

export default {
  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      selectedArtical: null,
      news: [],
      current:1,
      maxPages:1,
      maxElements:1,
    }
  },
  created() {
    this.$axios.get(`/api/news/?page=${this.current}`).then((response) => {
      this.news = response.data;
    });
    this.$axios.get(`/api/news/max`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },
  updated(){
    this.$axios.get(`/api/news/max`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },
  methods: {
    newNewsClick(){
      this.$router.push('/newnews');
    },
    editClick(artical) {
      const articalId=artical.id;
      this.$router.push('/news/' + articalId);
    },
    deliteClick(artical) {
      const articalId = artical.id;
      this.$axios.delete('api/news/' + articalId).then((response) => {
        this.maxElements=response.data;

        this.maxElements = response.data ;

        if(this.maxElements%2 !==0){
          this.maxPages=Math.floor(this.maxElements / 2)+1;
        }else{
          this.maxPages=Math.floor(this.maxElements / 2);
        }
        if(this.current >this.maxPages){
          this.current=this.current-1;
        }
        this.$axios.get(`/api/news/?page=${this.current}`).then((response) => {
          this.news = response.data;
        });

        console.log("Max ELEMNT"+this.maxElements)
        console.log("MAx STranica"+this.maxPages)

      });

    },
    back(){
      if(this.current!=1)
      {
        this.current = this.current - 1;
        this.$axios.get(`/api/news/?page=${this.current}`).then((response) => {
          this.news = response.data;
        });
        console.log(this.current)
      }
      },
    next(){

      if(this.current!=this.maxPages)
      {
        this.current = this.current + 1;
        this.$axios.get(`/api/news/?page=${this.current}`).then((response) => {
          this.news = response.data;
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
<style>
.container {
  display: flex;
  align-items: center;
}
button {
  margin-right: 10px;
}
</style>