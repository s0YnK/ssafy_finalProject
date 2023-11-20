import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const board_API = `http://localhost:8080/board-api`;

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([]);
//   const profile = ref({});
//   const followerList = ref([]);
//   const followingList = ref([]);
  
  //전체목록가져오기
  const getBoardList = function () {
    axios({
      method: "GET",
      url: `${board_API}/board/list`,
    }).then((response) => {
      boardList.value = response.data;
    });
  };

    //게시글 등록
  const createBoard = function (board) {
    console.log(board);
    axios({
      url: `${board_API}/board`,
      method: "POST",
      data: board,
    })
      .then(() => {
        router.push({ name: "community" });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  //게시글 하나보기
    const board = ref({});
    const getBoard = function (id) {
        axios.get(`${board_API}/board/${id}`).then((response) => {
            board.value = response.data;
        });
    };

//   const getProfile = function (userId) {
//     axios({
//       method: "GET",
//       url: `${Exercise_API}/user/profile`,
//       params: {
//         userId: userId,
//       },
//     }).then((response) => {
//       profile.value = response.data;
//     });
//   };
//   const getfollower = function (userId) {
//     axios({
//       method: "GET",
//       url: `${Exercise_API}/user/list/follower`,
//       params: {
//         userId: userId,
//       },
//     }).then((response) => {
//       followerList.value = response.data;
//     });
//   };
//   const getfollowing = function (userId) {
//     axios({
//       method: "GET",
//       url: `${Exercise_API}/user/list/following`,
//       params: {
//         userId: userId,
//       },
//     }).then((response) => {
//       followingList.value = response.data;
//     });
//   };
//   const followAdd = function (data) {
//     axios({
//       method: "post",
//       url: `${Exercise_API}/user/follow`,
//       data: data
//     }).then((response) => {
//       getfollowing(data.followingId.value);
//       getfollower(data.userId.value);
//       window.location.href = `http://localhost:5173/otherProfile/${data.followingId}`
//     });
//   };
//   const followDelete = function (data) {
//     axios({
//       method: "delete",
//       url: `${Exercise_API}/user/follow`,
//       data: data
//     }).then((response) => {
//       getfollower(data.userId.value);
//       getfollowing(data.followingId.value);
//     });
//   };

  return { getBoardList, boardList, // 보드리스트
            createBoard, //게시물 생성
            getBoard, board, //게시물 하나보기
};
});

