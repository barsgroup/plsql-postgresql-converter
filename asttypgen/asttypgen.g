grammar asttypgen;

ID: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
PERIOD: '.';
KW_PACKAGE: '@package';
KW_TOKEN_VOCAB: '@tokenVocab';
KW_TOKEN_TEXT: '@tokenText';
KW_PARSER_CLASS: '@parserClass';
COLON: ':';
SEMICOLON: ';';
SLASH: '/';
QUESTION: '?';
PLUS: '+';
ASTERISK: '*';
EQ: '=';
BRACKET_L: '[';
BRACKET_R: ']';
CURLY_L: '{';
CURLY_R: '}';
ARROW_R: '=>';
WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;};

astSpec returns [AstNodes.AstSpec spec]:
  { $spec = new AstNodes.AstSpec(); }
  packageNameDef[$spec]
  tokenVocabName[$spec]
  parserClassName[$spec]
  (r=ruleSpec { $spec.rules.add(r); })* EOF;
  
packageNameDef[AstNodes.AstSpec spec]:
  KW_PACKAGE r=ID { $spec.packageName.add($r.text); } (PERIOD r=ID { $spec.packageName.add($r.text); })*;

tokenVocabName[AstNodes.AstSpec spec]:
  KW_TOKEN_VOCAB r=ID { $spec.tokenVocabName.add($r.text); } (PERIOD r=ID { $spec.tokenVocabName.add($r.text); })*;
  
parserClassName[AstNodes.AstSpec spec]:
  KW_PARSER_CLASS r=ID { $spec.parserClassName = $r.text; };

ruleSpec returns [AstNodes.RuleSpec result]:
  r1=ruleWithoutAlternatives { $result = $r1.result; }
  | r2=ruleWithAlternatives { $result = $r2.result; };

ruleWithoutAlternatives returns [AstNodes.RuleWithoutAlts result]:
  ID COLON ruleBody SEMICOLON
  {
    $result = new AstNodes.RuleWithoutAlts();
    $result.name = $ID.text;
    $result.body = $ruleBody.result;
  };
  
ruleWithAlternatives returns [AstNodes.RuleWithAlts result]:
  ruleName=ID
  {
    $result = new AstNodes.RuleWithAlts();
    $result.name = $ruleName.text;
  }
  ARROW_R
  (
    altName=ID
    { $result.alternatives.add($altName.text); }
  )+
  SEMICOLON;
  
ruleBody returns [AstNodes.RuleBody result]:
  ID
  {
    $result = new AstNodes.RuleBody();
    $result.rootType = $ID.text;
  }
  (
    r=ruleItem
    { $result.items.add($r.result); }
  )*;

ruleItem returns [AstNodes.RuleItem result]:
  (propSpec EQ)? propMatchSpec
  {
    $result = new AstNodes.RuleItem();
    $result.propMatchSpec = $propMatchSpec.result;
    if ($propSpec.result != null) {
      $result.propSpec = $propSpec.result;
    } else {
      $result.propSpec = $result.propMatchSpec.createDefaultPropSpec();
    }
  };
  
propSpec returns [AstNodes.PropSpec result]:
{ $result = new AstNodes.PropSpec(); }
  CURLY_L
  (
    QUESTION { $result.isQuestion = true; }
    | BRACKET_L BRACKET_R { $result.isArray = true; }
    |
  )
  ID CURLY_R
{ $result.name = $ID.text; };

propMatchSpec returns [AstNodes.PropMatchSpec result]:
  KW_TOKEN_TEXT
  {
    $result = new AstNodes.PropMatchSpec();
    $result.isTokenText = true;
  }
  |
  ID
  {
    $result = new AstNodes.PropMatchSpec();
    $result.name = $ID.text;
  }
  (
    QUESTION { $result.isQuestion = true; }
    | ASTERISK { $result.isAsterisk = true; }
    | PLUS { $result.isPlus = true; }
    |
  );