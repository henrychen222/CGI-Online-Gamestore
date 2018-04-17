#!/usr/bin/perl
use CGI;
$query  = new CGI;

$asin  = $query->param( 'Asin' );
$names  = $query->param( 'Names' );



  # Remove leading and trailing spacing.
  $asin =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $asin =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $names =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $names =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom addDeveloperToGame_zqw";
$cmd   .= " '". $asin . "'";
$cmd   .= " '". $names . "'";

print( $cmd );
system( $cmd );



