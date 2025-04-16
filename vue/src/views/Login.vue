<template>
  <div class="pt-5">
    <h1 v-if="username">Hello, {{username}}</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Username</label>
        <input v-model="username" type="text" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter username">
        <small id="usernameHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input v-model="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
      </div>
      <button type="submit" class="btn btn-primary mt-2">Submit</button>
    </form>
  </div>


</template>

<script>
import jwt from 'jsonwebtoken';

export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    login() {
      this.$axios.post('/api/users/login', {
        username: this.username,
        password: this.password,
      }).then(response => {
        const decoded = jwt.decode(response.data.jwt);
        if (decoded.status === 'aktivan') {
          console.log(decoded);
          localStorage.setItem('email', decoded.sub);
          localStorage.setItem('role', decoded.role);
          localStorage.setItem('ime', decoded.ime);
          localStorage.setItem('prezime', decoded.prezime);
          localStorage.setItem('status', decoded.status);
          localStorage.setItem('id',decoded.id);
          localStorage.setItem('jwt', response.data.jwt);
          this.$router.push({ name: 'Home' });
        } else {
          window.alert('Korisnik je Neaktivan.');
        }
      })
    },

  },
}
</script>

<style scoped>

</style>
