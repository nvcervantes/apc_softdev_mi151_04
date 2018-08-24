<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "poll".
 *
 * @property integer $id
 * @property string $description
 * @property string $question
 * @property string $options
 * @property integer $user_id
 *
 * @property Commuter $user
 */
class Poll extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'poll';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'user_id'], 'required'],
            [['id', 'user_id'], 'integer'],
            [['description', 'question', 'options'], 'string', 'max' => 45],
            [['user_id'], 'exist', 'skipOnError' => true, 'targetClass' => Commuter::className(), 'targetAttribute' => ['user_id' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'description' => 'Description',
            'question' => 'Question',
            'options' => 'Options',
            'user_id' => 'User ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(Commuter::className(), ['id' => 'user_id']);
    }
}
