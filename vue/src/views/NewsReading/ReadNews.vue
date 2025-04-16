<template>
  <div id="news">
    <img alt="Vue logo" src="../../assets/logo.png">
    <h1 class="mt-4">News</h1>


    <div class="row">
      <div class="col-4">
        <table class="table">
          <thead>
          <tr>
            <!--            <th scope="col">ID</th>-->
            <th scope="col">Title</th>
            <th scope="col">Kategorija</th>
            <th scope="col">Content</th>
            <th scope="col">Date</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="artical in news" :key="artical.id" @click=goToNews(artical.id)>
            <!--            <th scope="row">{{ artical.id }}</th>-->
            <td> {{artical.title}}</td>
            <td>{{artical.kategorijaOBJ.title}} </td>
            <td>{{artical.kategorijaOBJ.content}} </td>
            <td>{{ formatTimestamp(artical.date) }}</td>
          </tr>
          </tbody>
        </table>

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
      news: []
    }
  },
  created() {
    this.$axios.get('/api/public/news/sortedDate').then((response) => {
      this.news = response.data;
    });
  },
  mounted() {
    this.$axios.get('/api/public/news/sortedDate').then((response) => {
    this.news = response.data;
  });
  },
  methods: {
    formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };
      return date.toLocaleDateString('en-US', options);
    },
    goToNews(id){
      this.$axios.post('/api/public/news/incrementClicks/',{
       id
      })
      this.$router.push('/news/read/'+ id);
    }
  }
}

</script>

<style scoped>

</style>