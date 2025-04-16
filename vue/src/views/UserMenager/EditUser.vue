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

    <div class="left">
      <h4>tip</h4>
      <select v-model="tip">
        <option value="admin">Admin</option>
        <option value="content">Content</option>
      </select>

      <button @click="sendNewKategorijaClick(ime,prezime,email,tip)">Add</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "EditUser",
  data() {
    return {
      user: {},
      ime:"",
      id1:0,
      prezime:"",
      email:"",
      tip:""
    }
  },
  created() {
    this.$axios.get(`/api/users/${this.$route.params.id}`).then((response) => {
      this.user = response.data;
      this.id1=this.user.id;
      this.ime = this.user.ime;
      this.email = this.user.email;
      this.prezime=this.user.prezime
      this.tip = this.user.tip;

    });
  },
  methods:{
    // sendNewKategorijaClick(ime,prezime,email,tip,){
    //   const id=this.id1;
    //   console.log(id);
    //   console.log(ime);
    //   console.log(prezime);
    //   console.log(email);
    //   console.log(tip);
    //
    //   this.$axios.post('/api/users/edit',{
    //     id,ime,prezime,email,tip,
    //   })
    //   this.$router.push('/users');
    //
    // },
    sendNewKategorijaClick(ime,prezime,email,tip,){
      if(ime !="" && prezime !="" && email !="" && tip !="" ) {
        console.log(ime);
        console.log(prezime);
        console.log(email);
        console.log(tip);


        this.$axios.get(`/api/users/checkEmail?email=${email}`).then((response) => {
            const id=this.id1;

          if(response.data){
            if(email.includes('@')){
              this.$axios.post('/api/users/edit', {
                id,ime, prezime, email, tip
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
  },

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