<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210312085658 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fichier DROP FOREIGN KEY FK_9B76551F611C0C56');
        $this->addSql('ALTER TABLE fichier DROP FOREIGN KEY FK_9B76551FD44F05E5');
        $this->addSql('DROP INDEX IDX_9B76551FD44F05E5 ON fichier');
        $this->addSql('DROP INDEX IDX_9B76551F611C0C56 ON fichier');
        $this->addSql('ALTER TABLE fichier DROP dossier_id, DROP images_id, CHANGE image images VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE images DROP image_name');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fichier ADD dossier_id INT DEFAULT NULL, ADD images_id INT DEFAULT NULL, CHANGE images image VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE fichier ADD CONSTRAINT FK_9B76551F611C0C56 FOREIGN KEY (dossier_id) REFERENCES dossier (id)');
        $this->addSql('ALTER TABLE fichier ADD CONSTRAINT FK_9B76551FD44F05E5 FOREIGN KEY (images_id) REFERENCES images (id)');
        $this->addSql('CREATE INDEX IDX_9B76551FD44F05E5 ON fichier (images_id)');
        $this->addSql('CREATE INDEX IDX_9B76551F611C0C56 ON fichier (dossier_id)');
        $this->addSql('ALTER TABLE images ADD image_name VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
