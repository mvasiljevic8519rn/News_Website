<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Web programiranje</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">

            <li class="nav-item" v-if="isLoggedIn">
              <router-link :to="{name: 'News' }" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'News'}">NewsHomePage</router-link>
            </li>

            <li class="nav-item" >
              <router-link :to="{name: 'Read-news'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'ReadNews'}">ReadNews</router-link>
            </li>

            <li class="nav-item" v-if="isLoggedIn">
              <router-link :to="{name: 'Kategorije'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Kategorije'}">Kategorije</router-link>
            </li>
            <li class="nav-item" v-if="isAdmin" >
              <router-link :to="{name: 'Users'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Users'}">Users</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'NewsTopTen'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'NewsTopTen'}">NewsTopTen</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'AllNewsInKategorije'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'AllNewsInKategorije'}">NewByKategorije</router-link>
            </li>
          </ul>
          <form v-if="canLogout" class="d-flex" @submit.prevent="logout">
            <button class="btn btn-outline-secondary" type="submit">Logout</button>
          </form>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavBarReader",
  data() {
    return {
      isLoggedIn: false,
      isAdmin: false
    };
  },

  computed: {

    canLogout() {
      return this.$route.name !== 'Login';
    },

  },
  methods: {
    logout() {
      localStorage.removeItem('jwt');
      localStorage.removeItem('email');
      localStorage.removeItem('role');
      localStorage.removeItem('ime');
      localStorage.removeItem('prezime');
      localStorage.removeItem('status');
      this.isLoggedIn = false; // Update login status
      this.isAdmin= false;
      this.$router.push({name: 'Login'});
    },

  },
  mounted() {
    this.isLoggedIn = localStorage.getItem('jwt') !== null;
  },updated() {
    if(localStorage.getItem('jwt')!==null){
      this.isLoggedIn=true;
    }
    if(localStorage.getItem('role')=='admin'){
      this.isAdmin=true;
    }
  }
}
</script>

<style scoped>

</style>