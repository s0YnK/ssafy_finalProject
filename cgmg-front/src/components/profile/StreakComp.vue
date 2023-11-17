<template>
    <div class="streak">
        <div class="cont">
            <svg width="250" height="1350" class="js-calendar-graph-svg d" id="dd">
                <!-- Graph -->
                <g transform="translate(11, 11)" id="graph"></g>

                <!-- Legend for contribution levels -->
                <g transform="translate(11, 33)">
                    <!-- ... (이전과 동일) -->
                </g>

                <!-- The labels on the left side of the graph -->
                <g transform="translate(0, 11)">
                    <text x="0" y="15" class="label">S</text>
                    <text x="0" y="40" class="label">M</text>
                    <!-- Add more weekday labels -->
                </g>

                <!-- The labels on the top of the graph -->
                <g transform="translate(11, 0)">
                    <text x="0" y="9" class="label">Jan</text>
                    <text x="21" y="9" class="label">Feb</text>
                    <!-- Add more month labels -->
                </g>
            </svg>
        </div>

        <!-- Input for each day -->
        <div>
            <label for="inputDate">Select Date: </label>
            <input type="date" v-model="inputDate">
            <label for="inputData">Enter Data: </label>
            <input type="number" id="inputData" min="0" max="100" v-model="inputData">
            <button @click="updateData">Update</button>
        </div>
    </div>    <!-- Input for each day -->
        <div>
          <label for="inputDate">Select Date: </label>
          <input type="date" v-model="inputDate">
          <label for="inputData">Enter Data: </label>
          <input type="number" id="inputData" min="0" max="100" v-model="inputData">
          <button @click="updateData">Update</button>
        </div>
      </div>
</template>