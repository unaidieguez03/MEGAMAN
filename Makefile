NAME              = TWO_DIMENSION_GAME 
MAIN              = main.Main
SOURCE_DIR        = src/
BIN_DIR           = bin/
RELEASE_DIR       = $(BIN_DIR)release/
DEBUG_DIR         = $(BIN_DIR)debug/
JAVA              = java
JAVA_COMPILER     = javac
JAVA_DEBUG_FLAGS  = -g
SRC               = $(wildcard $(SOURCE_DIR)**/*.java)

.PHONY: debug release clean-all clean run-debug run-release

compile-release: dir-release
	@$(JAVA_COMPILER) -d $(RELEASE_DIR) $(SRC)

compile-degun: dir-debug 
	@$(JAVA_COMPILER) $(JAVA_DEBUG_FLAGS) -d $(DEBUG_DIR) $(SRC)

dir-debug: 	
	@mkdir -p $(DEBUG_DIR)

dir-release: 	
	@mkdir -p $(RELEASE_DIR)
clean:
	@rm -rf $(DEBUG_DIR) 
	@rm -rf $(RELEASE_DIR) 

clean-all:clean
	@rm -rf $(DEBUG_DIR) 
	@rm -rf $(RELEASE_DIR) 
	@rmdir $(BIN_DIR) 

run-debug:clean compile-degun
	@$(JAVA) -cp $(DEBUG_DIR) $(MAIN)

run-release:clean compile-release
	@$(JAVA) -cp $(RELEASE_DIR) $(MAIN)

release: clean compile-release

debug: clean compile-degun
