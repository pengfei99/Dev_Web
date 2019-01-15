<br>
<div class="progress">

    <g:if test="${pwdStrength == 'Unknown'}">
        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">

        </div>
        <span style="color:black"> Unknown password </span>
    </g:if>


    <g:if test="${pwdStrength == 'Extremely_weak'}">
        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100" style="width:15%">

        </div>
        <span style="color:black">Extremely weak password</span>

    </g:if>

    <g:if test="${pwdStrength == 'Weak'}">
        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width:30%">
        </div>
        <span style="color:black">Weak password</span>
    </g:if>

    <g:if test="${pwdStrength == 'Medium'}">
        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:45%">
            Medium password
        </div>
    </g:if>

    <g:if test="${pwdStrength == 'Strong'}">
        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:60%">
            Strong password
        </div>
    </g:if>

    <g:if test="${pwdStrength == 'Very_strong'}">
        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width:80%">
            Very strong password
        </div>
    </g:if>

    <g:if test="${pwdStrength == 'Over_kill'}">
        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
            Over kill password
        </div>
    </g:if>

</div>

<div class="well">

        <ul>
            <li>Your password should contain at least 8 letters</li>
            <li>Your password should contain capital letters</li>
            <li>Your password should contain special letters (e.g. !@#$%^&*)</li>
        </ul>

</div>



