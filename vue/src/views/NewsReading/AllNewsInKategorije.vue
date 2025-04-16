<template>
  <div id="news">
    <img alt="Vue logo" src="../../assets/logo.png">
    <h1 class="mt-4">News</h1>

    <div class="row">
      <div class="col-4" v-for="kategorija in kategorije" :key="kategorija.id">
        <h2>{{ kategorija.title }}</h2>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">Title</th>
            <th scope="col">Kategorija</th>
            <th scope="col">Content</th>
            <th scope="col">Date</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="artical in getArticlesByKategorija(kategorija.id)" :key="artical.id" >
            <td @click="goToNews(artical.id)">{{ artical.title }}</td>
            <td>{{ artical.kategorijaOBJ.title }}</td>
            <td>{{ artical.kategorijaOBJ.content }}</td>
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
      kategorije:[],
      tables: [],
      news:[]
    }
  },
  created() {
    this.$axios.get(`/api/public/kategorijeNames`).then((response) => {
      this.kategorije = response.data;
      this.fetchNewsForKategorije();
    });

  },
  methods: {
    formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };
      return date.toLocaleDateString('en-US', options);
    },
    fetchNewsForKategorije() {
      // Iterate through each kategorija
      this.kategorije.forEach((kategorija) => {
        // Make an API call to fetch news for the current kategorija
        this.$axios
            .get(`/api/public/news/inKategorija/${kategorija.id}`)
            .then((response) => {
              this.news.push({
                kategorija: kategorija,
                articles: response.data,
              });
            });
      });
    },
    getArticlesByKategorija(kategorijaId) {
      const kategorijaNews = this.news.find(
          (item) => item.kategorija.id === kategorijaId
      );
      return kategorijaNews ? kategorijaNews.articles : [];
    },
    goToNews(articalId){
      this.$router.push('/news/read/'+ articalId);
    }

    }
}

</script>

<style scoped>

</style>