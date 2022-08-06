import Vue from 'vue'
import Vuex from 'vuex'

import common from "./modules/common";
import user from "./modules/user";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  modules:{
    common,
    user
  },
  getters:{

  }
})
