# Requirements.
---
* `Docker` for running the containerized version of `CSpell`

# Setup.
---
There are 2 `bash` files inside `root/.extras/` (`root` is whatever name the main directory was called)
-  `setup_hook.sh`: 
	- Copies 3 files into `.git/hooks/`. They are used for checking:
	- Code: style, testing coverage, spelling.
	- Commits messages format.
	- Branches name format.

-  `setup_cspell_docker.sh`:
	- Builds the CSpell docker image.

Execute this files from the root directory.
- `./.extras/<file>.sh`

# Tools used.
---
* [Gradle](https://gradle.org/) as its build system.
* [CSpell](https://cspell.org/) for spell checking.

## Use of Gradle
---
Gradle has many options, all of them executed through `./gradlew <option>`.  A list of them can be seen through the `tasks` option.

The ones we're interested in are:
-  `build`: To build the project, without executing it.
-  `run`: To build and run the project.
-  `test jacocoTestReport`: Applies code formatting steps to sourcecode in-place.
-  `test jacocoTestCoverageVerification`: Applies code formatting steps to sourcecode in-place.
-  `spotlessApply`: Applies code formatting steps to sourcecode in-place.
-  `spotlessCheck`:  Checks that sourcecode satisfies formatting steps.

## Use of CSpell
---
The `github-ci` will check for spelling mistakes in `.java` and `.md` files, so it's useful to be able to run this locally.

CSpell is used through it's docker image. So, if run on the root directory, it should be executed like:
`docker run --rm -v "$PWD":/app cspell -c TP2/.cspell.json --file TP2/**/*.java`
`docker run --rm -v "$PWD":/app cspell -c TP2/.cspell.json --file TP2/**/*.md`


Extras:
-  If not on the root directory, replace`TP2/.cspell.json` for wherever there's a cspell configuration json file
- This line `--file TP2/**/*.md` indicates what type of files will be checked (recursively), replace for any other type.
# Repository rules
---
## PR
---
To be approved, PR need:
* Review by 2 out of 4.
* Pipeline must succeed.
* Must be linked to an ISSUE ticket.

## Commits - Branches
---
Commits and branches use a specific format.
- Commits: `ISSUE-<number>: <Description>`
- Branches: `(feature|fix)/ISSUE-<number>-<Description>`

These are checked before committing.

## Coding
---
Code must use Microsoft style
```java
public void foo()
{
	  // Code
}
```

Use [Oracle's naming convention](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
```java
	class ImageSprite();
	Background getBackground();
	int backgroundWidth;
	int MAX_BACKGROUND_WIDTH = 4096;
```

## Testing Percentage
---
- Coverage >= 30%
