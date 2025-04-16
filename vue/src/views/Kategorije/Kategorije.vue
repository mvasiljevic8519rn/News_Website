<template>
  <div class="kategorije">
    <h1 class="mt-4">Kategorije</h1>

    <div class="row">
      <div class="col-4">
        <button @click="handleClick">Nova Kategorija</button>

        <table class="table">
          <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
            <th scope="col">Edit Kategorija</th>
            <th scope="col">Delite Kategorija</th>

          </tr>
          </thead>
          <tbody>
          <tr v-for="kategorija in kategorije" :key="kategorija.id"  >
            <th scope="row">{{ kategorija.id }}</th>
            <td @click=goToNews(kategorija.id) >{{ kategorija.title | capitalize }}</td>
            <td>{{ kategorija.content | shortText }}</td>
            <td>
              <button @click="editClick(kategorija)">Edit</button>
            </td>
            <td>
              <button @click="deliteClick(kategorija)">Delite</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="container">
        <button @click="back()">back</button>
        <h3>{{ current }}</h3>
        <button @click="next()">next</button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  filters: {
    shortText(value) {
      if (value.length < 10) {
        return value;
      }
      return value.slice(0, 10) + '...'
    }
  },
  data() {
    return {
      selectedKategorija: null,
      kategorije: [],
      current:1,
      maxPages:1,
      maxElements:1
    }
  },

  created() {
    console.log("Created")
    this.$axios.get(`/api/kategorije/?page=${this.current}`).then((response) => {
      this.kategorije = response.data;
    });
    this.$axios.get(`/api/kategorije/max`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },
  updated(){
    this.$axios.get(`/api/kategorije/max`).then((response) => {
      this.maxElements = response.data ;
      if(this.maxElements%2 !==0){
        this.maxPages=Math.floor(this.maxElements / 2)+1;
      }else{
        this.maxPages=Math.floor(this.maxElements / 2);
      }
    });
  },
  methods: {
    handleClick() {
      this.$router.push('/newkategorija');
    },
    editClick(kategorija){
      const kategorijaId=kategorija.id;
      this.$router.push('/kategorije/'+kategorijaId);
    },
    goToNews(Id){
      this.$router.push('/newsKategorija/'+Id);
    },
    deliteClick(kategorija) {
      const kategorijaId = kategorija.id;
      console.log(kategorijaId)

      this.$axios.get(`/api/kategorije/delete/?id=${kategorija.id}`).then((response) => {
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
        this.$axios.get(`/api/kategorije/?page=${this.current}`).then((response) => {
          this.kategorije = response.data;
        });

        console.log("Max ELEMNT"+this.maxElements)
        console.log("MAx STranica"+this.maxPages)

      });

    },

    back(){
      if(this.current!=1)
      {
        this.current = this.current - 1;
        this.$axios.get(`/api/kategorije/?page=${this.current}`).then((response) => {
          this.kategorije = response.data;
        });
        console.log(this.current)
      }
    },
    next(){
      if(this.current!=this.maxPages)
      {
        this.current = this.current + 1;
        this.$axios.get(`/api/kategorije/?page=${this.current}`).then((response) => {
          this.kategorije = response.data;
        });
        console.log(this.current)
      }
      console.log(this.current)
    },

  }
}
</script>


<style scoped>
.container {
  display: flex;
  align-items: center;
}
button {
  margin-right: 10px;
}
</style>