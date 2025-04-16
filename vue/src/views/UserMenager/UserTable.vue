<template>
  <div class="users">
    <h1 class="mt-4">Users</h1>

    <div class="row">
      <div class="col-4">
        <button @click="handleClick">Novi korisnik</button>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Ime</th>
            <th scope="col">Prezime</th>
            <th scope="col">Role</th>
            <th scope="col">Status</th>
            <th scope="col">Deactivate</th>
            <th scope="col">Delite user</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users" :key="user.id" >
            <th scope="row">{{ user.email }}</th>
            <td>{{ user.ime  }}</td>
            <td>{{ user.prezime }}</td>
            <td>{{ user.tip }}</td>
            <td>{{ user.aktivnost }}</td>
            <td>
              <button v-if="user.tip === 'content'" @click="deactivateClick(user)">Deactivate</button>
            </td>
            <td>
              <button @click="editClick(user)">Edit</button>
            </td>
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
      selectedUser: null,
      users: []
    }
  },
  created() {
    this.$axios.get('/api/users').then((response) => {
      this.users = response.data;
    });
  },
  updated() {
    this.$axios.get('/api/users').then((response) => {
      this.users = response.data;
    });
  },
  // updated() {
  //   this.$axios.get('/api/users').then((response) => {
  //     this.users = response.data;
  //   });
  // },
  methods: {
    handleClick() {
      this.$router.push('/newUser');
    },
    deactivateClick(user){
      //this.$router.push('/editUser');
      const id=user.id;
      console.log(id);
      this.$axios.post(`/api/users/`+id,{}).then(() => {
        this.$axios.get('/api/users').then((response) => {
          this.users = response.data;
        });
      });
    },
    editClick(user){
      const userId=user.id;
      this.$router.push('/editUser/'+userId);
    }
  }
}
</script>