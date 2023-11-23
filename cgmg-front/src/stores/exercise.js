import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";
import { useUserStore } from "@/stores/user";

const Exercise_API = `http://localhost:8080/exercise-api`;

// exercise.js
export const useExerciseStore = defineStore('exercise', () => {
  const exerciseList = ref([]);
  const exercisePart = ref([]);
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
  const getExercisePart = function (userId) {
    axios({
      method: "GET",
      url: `${Exercise_API}/exercise/log/part`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      exercisePart.value = response.data;
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
    .then(() => {
      // const store = useUserStore()
      // store.getProfile(JSON.parse(localStorage.getItem("loginUser")).userId)
      setTimeout(() => router.push('/exercise'), 100)
    });
  };

  return { exerciseList, getExerciseList,exerciseLog, getLankList, rankList, getExercisePart, exercisePart };
});

