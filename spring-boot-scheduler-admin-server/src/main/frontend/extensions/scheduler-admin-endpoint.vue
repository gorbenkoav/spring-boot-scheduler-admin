<template xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div class="scheduler-admin">
        <table class="table is-fullwidth">
            <thead>
            <tr>
                <th>Имя</th>
                <th>Id</th>
                <th>Расписание</th>
                <th>Активность</th>
            </tr>
            </thead>
            <tbody v-for="task in tasks" :key="task.id">
            <tr v-bind:style="[task.isEnable ? {'color': 'green'} : {'color': 'grey'}]">
                <td v-text="task.name"/>
                <td v-text="task.id"/>
                <td v-text="task.timetable"/>
                <td>
                    <button v-if="task.isEnable" v-bind:style="{'color': 'grey'}" v-on:click="enableTask(task)">
                        Pause
                    </button>
                    <button v-else="task.isEnable" v-bind:style="{'color': 'green'}" v-on:click="enableTask(task)">
                        Start
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    var tasks = [{
        name: 'Sample One',
        id: 'ag.tools.admin.scheduler.client.Schedulers.sampleOne',
        timetable: '1 sec',
        isEnable: true
    },
        {
            name: 'Sample Two',
            id: 'ag.tools.admin.scheduler.client.Schedulers.sampleTwo',
            timetable: '5 sec',
            isEnable: false
        }
    ]

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
            tasks: tasks
        }),
        methods: {
            enableTask: function (task) {
                task.isEnable = !task.isEnable
            }
        },
        async created() {
            const response = await this.instance.axios.get('actuator/health');
            //data.tasks = response.data;
        }
    };
</script>

<style>
    .scheduler-admin {
        font-size: 20px;
        width: 80%;
    }
</style>