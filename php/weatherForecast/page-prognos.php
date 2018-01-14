<?php
/**
 * The template for displaying all pages.
 *
 * This is the template that displays all pages by default.
 * Please note that this is the WordPress construct of pages
 * and that other 'pages' on your WordPress site may use a
 * different template.
 *
 * @link https://codex.wordpress.org/Template_Hierarchy
 *
 * @package Glob
 */
get_header(); ?>
<div class="container">
	<div id="primary" class="content-area">
		<main id="main" class="site-main" role="main">
			<form id="formid" action="" method="POST">
					Stad <input type="text" name="city" value="t.ex. 'New York'"/>
					<input type="submit" name="submit" value="Hämta väderdata"/>
			</form>
			<?php
				if(isset($_POST['submit'])){
					
					$city = $_POST['city'];
					
					$data = file_get_contents("http://api.openweathermap.org/data/2.5/forecast?q=".$city."i&appid=59a8ac2688b1eb7824a6e348abb2556b&units=metric");
					$jsonObject = json_decode($data);

					$forecast = $jsonObject->list;
					
					$length = count($forecast) + 1;
					
					echo "<h3>".$city."</h3>";
					
					for ($i = 0; $i < $length; $i++) {
						$timestamp = $jsonObject->list[$i]->dt;
						$time = date("H.i", $timestamp);
						$datum = date("d.m",$timestamp);
						
						if($time == 18.00){
						 echo "<strong>".$datum." kl ".$time."</strong><br>Temperatur: ".$jsonObject->list[$i]->main->temp."°C<br>Väderbeskrivning: ".$jsonObject->list[$i]->weather[0]->main."<br>Vindhastighet: ".$jsonObject->list[$i]->wind->speed."m/s<br><br>";
						}
					}
					
					
				}
			?>
		</main><!-- #main -->
	</div><!-- #primary -->
	<?php get_sidebar(); ?>
</div>
<?php
get_footer();
