
<div class="col-xs-12 col-sm-6 register">
	<form action="/user/register" class="validation-engine" method="post"
		autocomplete="off">
		<h6>REGISTER</h6>
		<div class="required form-group">
			<p>User name or email adress *</p>
			<input type="text" name="username"
				class="form-control validate[required]"
				data-prompt-position="topLeft">
		</div>
		<div class="required form-group">
			<p>Password *</p>
			<input type="password" name="password"
				class="form-control validate[required]"
				data-prompt-position="topLeft">
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" name="login"
			class="btn btn-third-col" value="REGISTER">
	</form>
</div>
