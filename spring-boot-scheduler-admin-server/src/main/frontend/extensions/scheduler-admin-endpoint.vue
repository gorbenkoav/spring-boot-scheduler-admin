<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div class="container">
        <table class="table is-hoverable is-fullwidth">
            <thead>
            <tr>
                <th>Name</th>
                <th>Id</th>
                <th>Timetable</th>
                <th>Is enable</th>
            </tr>
            </thead>
            <tbody v-for="task in tasks" :key="task.id">
            <tr v-bind:style="[task.enable ? {'color': 'green'} : {'color': 'grey'}]">
                <td v-text="task.name"/>
                <td v-text="task.id"/>
                <td v-text="task.timetable"/>
                <div class="control">

                <td>
                    <button class="button is-light" v-if="task.enable" v-bind:style="{'color': 'grey'}" v-on:click="enableTask(task)">
                        Pause
                    </button>
                    <button class="button is-light" v-else="task.enable" v-bind:style="{'color': 'green'}" v-on:click="enableTask(task)">
                        Start
                    </button>
                </td>
                </div>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        props: {
            instance: {
                type: Object,
                required: true
            }
        },
        data: () => ({
            hasLoaded: false,
            error: null,
            tasks: []
        }),
        methods: {
            enableTask: function (task) {
                task.enable = !task.enable
            }
        },
        async created() {
            const response = await this.instance.axios.get('actuator/scheduler-admin');
            this.tasks = response.data;
        }
    };
</script>
