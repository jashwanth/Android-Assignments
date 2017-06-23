<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;
require '../myslim/vendor/autoload.php';
$app = new \Slim\App;

function getMovies() {
	$conn = getDb();
	$sql = "SELECT * FROM Movies ORDER BY name ASC";
	if (!$result = $conn->query($sql))
	{
		die('There was an error running the query [' . $conn->error . ']\n');
	}
	$return_arr = array();
	while ($row = $result->fetch_assoc()) 
	{
		$row_array['id'] = $row['id'];
		$row_array['name'] = $row['name'];
		$row_array['description'] = $row['description'];
		$row_array['rating'] = $row['rating'];
		$row_array['url'] = $row['url'];
		$row_array['director'] = $row['director'];
		$row_array['stars'] = $row['stars'];
		$row['year'] = "".$row['year'];
		$row_array['year'] = $row['year'];
		$row_array['length'] = $row['length'];
		array_push($return_arr, $row_array);
	}
	echo json_encode($return_arr);
	$conn->close();
}

function getDb() {
	$dbhost = "localhost";
	$dbuser = "root";
	$dbpass="";
	$dbname="androidmoviedb";

	$conn = new mysqli($dbhost, $dbuser, $dbpass, $dbname);

	if ($conn->connect_error) {
  	die("Connection failed: ".$conn->connect_error."\n");
	}
	
	return $conn;
}

function getMoviesAboveRating($ratings) { 
	$conn = getDb();
	if ($stmt = $conn->prepare("SELECT *
					FROM Movies
					WHERE rating >= ? ORDER BY rating DESC"))
	{
		// Bind parameters to the query
		$stmt->bind_param("s", $ratings);
		// Execute query
		$stmt->execute();
		//Gte query results it may contain multiple rows
		$result = $stmt->get_result();

		$return_arr = array();

		while ($row = $result->fetch_assoc()) 
		{
			$row_array['id'] = $row['id'];
			$row_array['name'] = $row['name'];
			$row_array['description'] = $row['description'];
			$row_array['rating'] = $row['rating'];
			$row_array['url'] = $row['url'];
			$row_array['director'] = $row['director'];
			$row_array['stars'] = $row['stars'];
			$row['year'] = "".$row['year'];
			$row_array['year'] = $row['year'];
			$row_array['length'] = $row['length'];
			array_push($return_arr, $row);
		}
		echo json_encode($return_arr);
		$stmt->close();
	}
	$conn->close();
}

function deleteMovieFromDb($movieid) {
	$conn = getDb();
	if ($stmt = $conn->prepare("DELETE FROM Movies
					WHERE id =  ? "))
	{
		// Bind parameters to the query
		$stmt->bind_param("s", $movieid);
		// Execute query
		$stmt->execute();
		//Gte query results it may contain multiple rows
		$result = $stmt->get_result();
		echo "Movie with id: $movieid has be deleted from the Database";
		$stmt->close();
	}
	else
	{
		echo $stmt."<p>";
		die('There was an error running the query [' . $conn->error . ']\n');
	}
	$conn->close();
}

function getMovieDetail($movieid) {
	$conn = getDb();
	//if ($stmt = $conn->prepare("SELECT id, name, description, rating, url, director, length, stars
	//				FROM Movies
	//				WHERE id =  ? "))
	if ($stmt = $conn->prepare("SELECT *
					FROM Movies
					WHERE id =  ? "))
	{
		// Bind parameters to the query
		$stmt->bind_param("s", $movieid);
		// Execute query
		$stmt->execute();
		//Gte query results it may contain multiple rows
		$result = $stmt->get_result();
		
		$return_arr = array();
		while ($row = $result->fetch_assoc()) {
			$row_array['id'] = $row['id'];
			$row_array['name'] = $row['name'];
			$row_array['description'] = $row['description'];
			$row_array['rating'] = $row['rating'];
			$row_array['url'] = $row['url'];
			$row_array['director'] = $row['director'];
			$row_array['stars'] = $row['stars'];
			$row['year'] = "".$row['year'];
			$row_array['year'] = $row['year'];
			$row_array['length'] = $row['length'];
			array_push($return_arr, $row);
		}
		echo json_encode($return_arr);
		$stmt->close();
	}

	else
	{
		echo $stmt."<p>";
		die('There was an error running the query [' . $conn->error . ']\n');
	}
	$conn->close();
}


/*$app->get('/', 
function(Request $request,Response $response) {
	$response->getBody()->write("Hello, OrangeNation");
	return $response ;
});*/


/*$app->post('/delete/id/{movieid}',
function(Request $request, Response $response, $args) {
//	$data = $request->getParsedBody();
  	deleteMovieFromDb($args['movieid']);
});*/

$app->post('/add', 
function(Request $request,Response $response) {
	//$data = $request->getParsedBody();
 	$data = json_decode($request->getBody(),true);

	echo 'This is a POST route.<br>';
	echo $data['id']."<br>";
	echo $data['name']."<br>";
	echo $data['description']."<br>";
	echo $data['stars']."<br>";
	echo $data['length']."<br>";
	echo $data['image']."<br>";
	echo $data['year']."<br>";
	echo $data['director']."<br>";
	echo $data['url']."<br>"; 
	echo $data['rating']."<br>"; 

	$conn = getDb();
	if ($stmt = $conn->prepare("INSERT INTO Movies (id, name, description, stars, length, image, year, rating, director, url)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ))
	{
		// Bind parameters to the query
		$stmt->bind_param("sssssssdss", $data['id'], $data['name'], $data['description'], $data['stars'],
						$data['length'], $data['image'], $data['year'], $data['rating'], $data['director'], $data['url']);
		// Execute query
		$stmt->execute();
		
	/*	if (!$result = $stmt->get_result())
		{
			die('Could not enter data: ' . mysql_error());
		}*/
		echo "Added the movie details to the database successfully\n";
		$stmt->close();
	}
	else
	{
		echo $stmt."<p>";
		die('There was an error running the query [' . $conn->error . ']\n');
	}
	$conn->close();
});

$app->get('/', 
	function() {
		echo "<h1>Android Programming 7</h1>";
		echo "<form action=\"http://jashwanth-reddy.com/add\" method=\"post\">";
		echo "ID: <input type=\"text\" name=\"id\"><br>";
		echo "Name: <input type=\"text\" name=\"name\"><br>";
		echo "Description: <input type=\"text\" name=\"description\"><br>";
		echo "Stars: <input type=\"text\" name=\"stars\"><br>";
		echo "Length: <input type=\"text\" name=\"length\"><br>";
		echo "Image: <input type=\"text\" name=\"image\"><br>";
		echo "Year: <input type=\"text\" name=\"year\"><br>";
		echo "Director: <input type=\"text\" name=\"director\"><br>";
		echo "URL: <input type=\"text\" name=\"url\"><br>";
		echo "<input type=\"submit\" value=\"Submit\">";
		echo "</form>"; 
});

$app->post(
 	'/delete',
 	function($request, $response){
 	//	$movieName = json_decode($request->getBody(),true);
	//	echo $request;
	//	$movieName = $request->getParsedBody();
 		$movieName = json_decode($request->getBody(),true);
/*		echo '<script language="javascript">';
  		echo 'alert('.$movieName.'); 
  		echo '</script>';*/
//              echo "Hello World";
//		echo $movieName['id'];
		deleteMovieFromDb($movieName['id']);
 });

$app->get(
 	'/delete',
 	function(){
 		echo "<h1> Specify the details of the movie to be deleted from the database</h1>";
 		echo "<h3> Movie name:</h3>";
 		echo "<form id=\"formName\" action =\"http://jashwanth-reddy.com/delete\" method=\"post\">";
 		echo "<pre>Movie Id : \t<input type=\"text\"name=\"id\"><br/></pre><br/>";
 		echo "<input type=\"submit\" value = \"Submit\"><br\>";
 		echo "</form>";
 	});

$app->get('/movies', 'getMovies');

$app->get('/movies/rating/{ratingsabove}',
function(Request $request, Response $response, $args) {
	// getMoviesAboveRating_NotSafe($args['ratingsabove']);	
	getMoviesAboveRating($args['ratingsabove']);
});


$app->get('/movies/id/{mid}',
function(Request $request, Response $response, $args) {
  getMovieDetail($args['mid']);
});

$app->run();
?>
