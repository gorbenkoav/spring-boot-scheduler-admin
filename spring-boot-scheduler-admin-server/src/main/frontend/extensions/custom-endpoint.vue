<template>
    <div class="custom">
        Instance: <span v-text="instance.id"/>
        Output: <span v-text="text"/>
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
            text: ''
        }),
        async created() {
            const response = await this.instance.axios.get('actuator/health');
            this.text = response.data;
        }
    };
</script>

<style>
    .custom {
        font-size: 20px;
        width: 80%;
    }
</style>