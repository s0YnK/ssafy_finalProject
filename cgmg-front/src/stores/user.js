import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const Exercise_API = `http://localhost:8080/user-api`;

export const useUserStore = defineStore('user', () => {
  const searchList = ref([]);
  
  const getSearch = function (word) {
    axios({
      method: "GET",
      url: `${Exercise_API}/user/search`,
      params: {
        word: word,
      },
    }).then((response) => {
      searchList.value = response.data;
    });
  };
  return { getSearch, searchList};
});

