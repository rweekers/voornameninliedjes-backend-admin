UPDATE song
SET firstname = REPLACE (title, '&eacute;', 'é')
WHERE firstname LIKE '%&eacute;%';
