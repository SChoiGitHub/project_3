defaultTest:
	mvn test -Durl=jdbc:mysql://localhost:3306/sys?useSSL=false -Duser=root -Dpassword=password

defaultBuild:
	mvn install -Durl=jdbc:mysql://localhost:3306/sys?useSSL=false -Duser=root -Dpassword=password

defaultRun:
	sudo java -jar ./target/project-1.0-jar-with-dependencies.jar

specificServerTest:
	mvn test -Durl=${url} -Duser=${user} -Dpassword=${password}

specificServerBuild:
	mvn install -Durl=${url} -Duser=${user} -Dpassword=${password}

specificServerRun:
	sudo java -jar ./target/project-1.0-jar-with-dependencies.jar ${url} ${user} ${password}

buildSkipTests:
	mvn install -DskipTests
