import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const Exercise_API = `http://localhost:8080/exercise-api`;

// exercise.js
export const useExerciseStore = defineStore('exercise', () => {
  const exerciseList = ref([]);
  const rankList = ref([]);
  
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
  const getLankList = function (part) {
    axios({
      method: "GET",
      url: `${Exercise_API}/rank/${part}`,
      params: {
        part: part,
      },
    }).then((response) => {
      rankList.value = response.data;
    });
  };
  const exerciseLog = function (exercise) {
    axios.post(Exercise_API+'/exercise/log', exercise)
    .then((response) => {
      router.push('/exercise')
    });
  };

  return { exerciseList, getExerciseList,exerciseLog, getLankList, rankList };
});

