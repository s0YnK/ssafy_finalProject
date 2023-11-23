import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const board_API = `http://localhost:8080/board-api`;

export const useBoardStore = defineStore('board', () => {
  // const boardList = ref([]);
  const BestList = ref([]);
  const OrderList = ref([]);
  const LikeList = ref([]);
  const MyList = ref([]);
  const LikeLog = ref(false);
//   const profile = ref({});
//   const followerList = ref([]);
//   const followingList = ref([]);
  
  // //전체목록가져오기
  // const getBoardList = function () {
  //   axios({
  //     method: "GET",
  //     url: `${board_API}/board/list`,
  //   }).then((response) => {
  //     boardList.value = response.data;
  //   });
  // };
  
  const getOrderList = function (data) {
    axios({
      method: "GET",
      url: `${board_API}/board/search`,
      params: data,
    }).then((response) => {
      console.log(response.data)
      OrderList.value = response.data;
    });
  };
  const getBestList = function () {
    axios({
      method: "GET",
      url: `${board_API}/board/search`,
      params: {
        key: null,
        word: null,
        orderBy: "viewCnt",
        orderByDir: "DESC",
      },
    }).then((response) => {
      console.log(response.data)
      BestList.value = response.data;
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
        console.log(board.gymAddress)
        router.push({ name: "community" });
      })
  };
    //게시글 수정
  const updateBoard = function (board) {
    axios.put(`${board_API}/board`, board).then(() => {
      router.push({ name: "community" });
    });
  };
  //게시글 하나보기
    const board = ref({});
    const getBoard = function (id) {
        axios.get(`${board_API}/board/${id}`).then((response) => {
            board.value = response.data;
        });
    };

    //좋아요하기
const likeBoard = function (data) {
    axios({
      url: `${board_API}/board/like`,
      method: "POST",
      data: data,
    })
      .then(() => {
        console.log("성공")
        likeLog(data)
      })
  };

  //좋아요취소하기
  const unlikeBoard = function (data) {
    axios({
      url: `${board_API}/board/like`,
      method: "delete",
      data: data,
    })
      .then(() => {
        console.log("취소성공")
        likeLog(data)
      })
  };

  //좋아요로그보기
  const likeLog = function (data) {
    axios({
      url: `${board_API}/board/like/log`,
      method: "get",
      params: data,
    })
      .then((response) => {
        console.log(response.data)
        LikeLog.value = response.data;
      })
  };

  //좋아요한 게시물 보기
  const getLikeList = function (userId) {
    axios({
      method: "GET",
      url: `${board_API}/board/like`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      console.log("업뎃성공")
      LikeList.value = response.data;
    });
  };
  //내가만든 게시물 보기
  const getMyList = function (userId) {
    axios({
      method: "GET",
      url: `${board_API}/board/mylist`,
      params: {
        userId: userId,
      },
    }).then((response) => {
      console.log("업뎃성공")
      MyList.value = response.data;
    });
  };



  return { 
            createBoard, //게시물 생성
            getBoard, board, //게시물 하나보기
            updateBoard, //게시물 수정
            LikeList, likeBoard, getLikeList, unlikeBoard, //좋아요관련
            MyList, getMyList, //내가 만든 리스트
            likeLog, LikeLog,
            getOrderList, OrderList,
            getBestList, BestList,
};
});

