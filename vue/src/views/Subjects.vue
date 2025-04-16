<template>
  <div class="subjects">
    <h1 class="mt-4">Subjects</h1>

    <div class="row">
      <div class="col-4">
        <table class="table">
          <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="subject in subjects" :key="subject.id" @click="selectedSubject = subject">
            <th scope="row">{{ subject.id }}</th>
            <td>{{ subject.title | capitalize }}</td>
            <td>{{ subject.content | shortText }}</td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="col-6">
        <subject v-if="selectedSubject" :subject="selectedSubject"></subject>
      </div>
    </div>
  </div>
</template>

<script>
import Subject from "../components/Subject";

export default {
  components: {Subject},
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
      selectedSubject: null,
      subjects: []
    }
  },
  created() {
    this.$axios.get('/api/subjects').then((response) => {
      this.subjects = response.data;
    });
  },
}
</script>
