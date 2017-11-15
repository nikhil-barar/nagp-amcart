
<div class="col-xs-12 col-sm-6 login">
	<form action="login" class="validation-engine">
		<h6>LOGIN</h6>
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
		<p>
			<input type="checkbox" name="remember" id="checkbox1" class="stl"
				value="2"> <label for="checkbox1" class="stl"><span></span>Remember
				me</label> <a href="#">Fogot your password?</a><br>
		</p>
		<input type="submit" name="login" class="btn btn-third-col"
			value="LOGIN">
	</form>
</div>