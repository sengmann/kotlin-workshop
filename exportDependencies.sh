cd ktor-demo && \
	mvn dependency:copy-dependencies -DoutputDirectory=libs && \
	cd ../spring-demo && \
	mvn dependency:copy-dependencies -DoutputDirectory=libs && \
	cd ..
