### Hexlet tests and linter status:
[![Actions Status](https://github.com/Ogeeon/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Ogeeon/java-project-78/actions)
[![Java CI](https://github.com/Ogeeon/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/Ogeeon/java-project-78/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Ogeeon_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Ogeeon_java-project-78)
### About
This project is a utility for content validation. It is not designed to be used as a standalone application.

### Usage
- To validate numbers, create an instance of Validator class and then create a checking schema:\
  `var v = new Validator();`\
  `var schema = v.number();`\
  Then add the necessary checks. Available checks are `positive` and `range`.  Also, the schema has a `required` method that prohibits using nulls. Calling `schema.isValid()` with a number will result in a boolean `true` or `false` depending on whether the input number passed imposed checks.\
  [Here](https://asciinema.org/a/3hgIM15SsPFVEvh9C8C332c9r) is an asciinema with demonstration.
- Validating strings is done in the same way. For string schema, created by `var schema = v.string();` available methods are `required`, `minLength` and `contains`. Subsequent calls of `minLength` overwrite the restriction, and subsequent calls of `contains` add more strings that should be found in the string being tested.\
  [Here](https://asciinema.org/a/o45y7AMX8CWX8kUaR7fQ7c9is) is an asciinema with demonstration.
- The user can also validate maps. For map schema, created by `var schema = v.map();` available methods are `required`, `sizeOf` to impose a minimum map size restriction, and `shape` which accepts a map where keys are the keys of the checked map and values are instances of string schema.\
  [Here](https://asciinema.org/a/Txlh22YZc8WZq5rVNoIpJM32P) is an asciinema with demonstration.