<template>
  <div>
    <br>
    <input
        class="title-input"
        v-model="ime"
        placeholder="Ime"/>
    <p>Ime</p>
    <input
        class="content-input"
        v-model="prezime"
        placeholder="Prezime"/>
    <p>Prezime</p>
    <input
        class="content-input"
        v-model="email"
        placeholder="Email"/>

    <p>Email</p>
    <input
        class="content-input"
        v-model="hashedPassword"
        placeholder="Password"/>
    <p>Sifra</p>
    <div class="left">
      <h4>tip</h4>
      <select v-model="tip">
      <option value="admin">Admin</option>
      <option value="content">Content</option>
    </select>

    <button @click="sendNewKategorijaClick(ime,prezime,email,tip,hashedPassword)">Add</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewUser",
  data() {
    return {
      user: {},
      ime:"",
      prezime:"",
      email:"",
      hashedPassword:"",
      tip:"admin"
    }
  },
  created() {
    this.ime="";
  },
  methods:{
    sendNewKategorijaClick(ime,prezime,email,tip,hashedPassword){
      if(ime !="" && prezime !="" && email !="" && tip !="" && hashedPassword !="") {
        console.log(ime);
        console.log(prezime);
        console.log(email);
        console.log(tip);
        console.log(hashedPassword);

        this.$axios.get(`/api/users/checkEmail?email=${email}`).then((response) => {
          if(response.data){
            if(email.includes('@')){
            this.$axios.post('/api/users', {
              ime, prezime, email, tip, hashedPassword
            })
            this.$router.push('/users');
            }
            else {
              window.alert('los format EMAIL');

            }
          }else {
            window.alert('VEC POSTOJI EMAIL');
          }
           console.log(response.data)
        });
      }
      else {
        window.alert('sva polja su obavezna.');
      }
    }


  }

}
</script>

<style scoped>

.left {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.left h6 {
  margin-right: 10px;
}
</style>