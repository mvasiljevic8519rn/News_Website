import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/subjects',
    name: 'Subjects',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Subjects.vue')
  },
  {
    path: '/subjects/:id',
    name: 'single-subject',
    meta: {
      authRequired: true,
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleSubject.vue')
  },
    //moje dodato

  {
    path:'/news',
    name:'News',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewsHomePage.vue')
  },
  {
    path:'/newstopten',
    name:'NewsTopTen',
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewsTopTen.vue')
  },
  {
    path:'/news/:id',
    name:'Single-news',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/SingleNews.vue')
  },
  {
    path:'/news/read',
    name:'Read-news',
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/ReadNews.vue')
  },
  {
    path:'/allNewsInKategorije',
    name:'AllNewsInKategorije',
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/AllNewsInKategorije.vue')
  },
  {
    path:'/news/read/:id',
    name:'Single-read-news',
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/ReadSingleNews.vue')
  },
  {
    path:'/newnews',
    name:'New-news',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewNews.vue')
  },
  {
    path:'/newsKategorija/:id',
    name:'newskategorija',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewsKategorija.vue')
  },
  {
    path:'/newsTag/:id',
    name:'newsTag',
    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewsTags.vue')
  },
  {
    path:'/kategorije',
    name:'Kategorije',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/Kategorije/Kategorije.vue')
  },
  {
    path:'/kategorije/:id',
    name:'Single-kategorija',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/Kategorije/SingleKategorija.vue')
  },
  {
    path:'/newkategorija',
    name:'New-kategorija',
    meta: {
      authRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/Kategorije/NewKategorija.vue')
  },
  {
    path:'/newComment',
    name:'New-comment',

    component: ()=> import(/* webpackChunkName: "about" */ '../views/NewsReading/NewComment.vue')
  },
  {
    path:'/users',
    name:'Users',
    meta: {
      adminRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/UserMenager/UserTable.vue')
  },
  {
    path:'/newUser',
    name:'NewUser',
    meta: {
      adminRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/UserMenager/NewUser.vue')
  },
  {
    path:'/editUser/:id',
    name:'EditUser',
    meta: {
      adminRequired: true,
    },
    component: ()=> import(/* webpackChunkName: "about" */ '../views/UserMenager/EditUser.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');
   // const admin = localStorage.getItem('tip');

    if (!jwt ) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    }
  }
  if (to.meta.adminRequired) {
    const jwt = localStorage.getItem('jwt');
     const admin = localStorage.getItem('role');

    if (admin !=='admin') {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    }
  }

  next();
});

export default router
