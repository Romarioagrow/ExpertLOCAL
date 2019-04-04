<div class="text-center">
    <form id="form">
        <p><label class="checkbox"><input type="checkbox" id="checkbox"> Отправить форму через AJAX</label></p>
    </form>
    <p id="result" class="text-success"></p>
</div>

<style>
    .checkbox {
        display: inline-block;
        margin-bottom: 0;
        line-height: 21px;
    }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script>
    $('#checkbox').click(function(){
        if ($(this).is(':checked')){
            $.ajax({
                url: 'http://localhost:8080/mda',
                method: 'post',
                dataType: 'html',
                data: $('#form').serialize(),
                success: function(data){
                    $('#result').html(data);
                }
            });
        }
    });
</script>