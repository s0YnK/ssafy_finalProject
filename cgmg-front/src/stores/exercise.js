import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const Exercise_API = `http://localhost:8080/exercise-api`;

// exercise.js
export const useExerciseStore = defineStore('exercise', () => {
  const exerciseList = ref([]);
  
  const getExerciseList = function (userId) {
    axios({
      method: "GET",
      url: `${Exercise_API}/exercise/log`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      exerciseList.value = response.data;
    });
  };

  return { exerciseList, getExerciseList };
});

